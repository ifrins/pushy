/*
 * Copyright (c) 2013-2018 Turo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.turo.pushy.apns.server;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.ssl.SslContext;

import javax.net.ssl.SSLSession;

public class BenchmarkApnsServer extends BaseHttp2Server {

    private final int maxConcurrentStreams;

    BenchmarkApnsServer(final SslContext sslContext, final EventLoopGroup eventLoopGroup, final int maxConcurrentStreams) {
        super(sslContext, eventLoopGroup);

        this.maxConcurrentStreams = maxConcurrentStreams;
    }

    @Override
    protected void addHandlersToPipeline(final SSLSession sslSession, final ChannelPipeline pipeline) {
        pipeline.addLast(new BenchmarkApnsServerHandler.BenchmarkApnsServerHandlerBuilder()
                .initialSettings(Http2Settings.defaultSettings().maxConcurrentStreams(this.maxConcurrentStreams))
                .build());
    }
}
