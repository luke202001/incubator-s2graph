/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.s2graph.spark

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterAll

class TestStreamingSpec extends Specification with BeforeAfterAll {
  private val master = "local[2]"
  private val appName = "test_streaming"
  private val batchDuration = Seconds(1)

  private var sc: SparkContext = _
  private var ssc: StreamingContext = _

  override def beforeAll(): Unit = {
    val conf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)

    ssc = new StreamingContext(conf, batchDuration)

    sc = ssc.sparkContext
  }

  override def afterAll(): Unit = {
    if (ssc != null) {
      ssc.stop()
    }
  }
}
