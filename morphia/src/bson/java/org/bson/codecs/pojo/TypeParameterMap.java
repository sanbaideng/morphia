/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bson.codecs.pojo;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;


/**
 * Maps the index of a class's generic parameter type index to a property's.
 */
public final class TypeParameterMap {
    private final Map<Integer, Integer> propertyToClassParamIndexMap;

    /**
     * Creates a new builder for the TypeParameterMap
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a mapping of property type parameter index to the class type parameter index.
     *
     * <p>Note: A property index of -1, means the class's parameter type represents the whole property</p>
     *
     * @return a mapping of property type parameter index to the class type parameter index.
     */
    public Map<Integer, Integer> getPropertyToClassParamIndexMap() {
        return propertyToClassParamIndexMap;
    }

    public boolean hasTypeParameters() {
        return !propertyToClassParamIndexMap.isEmpty();
    }

    /**
     * A builder for mapping field type parameter indices to the class type parameter indices
     */
    public static final class Builder {
        private final Map<Integer, Integer> propertyToClassParamIndexMap = new HashMap<Integer, Integer>();

        private Builder() {
        }

        /**
         * Adds the type parameter index for a class that represents the whole property
         *
         * @param classTypeParameterIndex the class's type parameter index that represents the whole field
         * @return this
         */
        public Builder addIndex(final int classTypeParameterIndex) {
            propertyToClassParamIndexMap.put(-1, classTypeParameterIndex);
            return this;
        }

        /**
         * Adds a mapping that represents the property
         *
         * @param propertyTypeParameterIndex the property's type parameter index
         * @param classTypeParameterIndex the class's type parameter index
         * @return this
         */
        public Builder addIndex(final int propertyTypeParameterIndex, final int classTypeParameterIndex) {
            propertyToClassParamIndexMap.put(propertyTypeParameterIndex, classTypeParameterIndex);
            return this;
        }

        /**
         * @return the TypeParameterMap
         */
        public TypeParameterMap build() {
            if (propertyToClassParamIndexMap.size() > 1 && propertyToClassParamIndexMap.containsKey(-1)) {
                throw new IllegalStateException("You cannot have a generic field that also has type parameters.");
            }
            return new TypeParameterMap(propertyToClassParamIndexMap);
        }
    }

    @Override
    public String toString() {
        return "TypeParameterMap{"
                + "fieldToClassParamIndexMap=" + propertyToClassParamIndexMap
                + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeParameterMap that = (TypeParameterMap) o;

        return getPropertyToClassParamIndexMap().equals(that.getPropertyToClassParamIndexMap());
    }

    @Override
    public int hashCode() {
        return getPropertyToClassParamIndexMap().hashCode();
    }

    private TypeParameterMap(final Map<Integer, Integer> propertyToClassParamIndexMap) {
        this.propertyToClassParamIndexMap = unmodifiableMap(propertyToClassParamIndexMap);
    }
}
