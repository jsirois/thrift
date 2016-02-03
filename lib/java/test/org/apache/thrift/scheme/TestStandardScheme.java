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

package org.apache.thrift.scheme;

import junit.framework.TestCase;

import org.apache.thrift.Fixtures;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TImmutableBase;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransport;

import thrift.test.HolyMoley;
import thrift.test.Nesting;
import thrift.test.OneOfEach;

public class TestStandardScheme extends TestCase {
  TSerializer serializer = new TSerializer();
  TDeserializer deserializer = new TDeserializer();

  /**
   * This tests whether the Standard Scheme properly reads structs serialized
   * using an older version of thrift.
   */
  public void testPersistentStructs() throws TException {
    readAndCompare(new OneOfEach(), Fixtures.oneOfEach, Fixtures.persistentBytesOneOfEach);
    readAndCompare(new HolyMoley(), Fixtures.holyMoley, Fixtures.persistentBytesHolyMoley);
    readAndCompare(new Nesting(), Fixtures.nesting, Fixtures.persistentBytesNesting);
  }

  public void readAndCompare(TBase struct, TImmutableBase fixture, byte[] inputBytes) throws TException {
    TTransport trans = new TMemoryBuffer(0);
    trans.write(inputBytes, 0, inputBytes.length);
    TProtocol iprot = new TBinaryProtocol(trans);
    struct.read(iprot);
    assertEquals(fixture, struct);
  }
}
