/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.thrift;

/**
 * Generic base interface for mutable generated Thrift objects.
 */
public interface TBase<T extends TBase<T, F>, F extends TFieldIdEnum> extends TImmutableBase<T, F>, TSerializable {

  /**
   * Set a field's value by field variable. Primitive types must be "boxed" in
   * the appropriate object wrapper type.
   *
   * @param field
   */
  void setFieldValue(F field, Object value);

  T deepCopy();

  /**
   * Return to the state of having just been initialized, as though you had just
   * called the default constructor.
   */
  void clear();
}
