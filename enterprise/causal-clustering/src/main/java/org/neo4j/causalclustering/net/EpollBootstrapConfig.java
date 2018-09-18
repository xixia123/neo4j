/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j Enterprise Edition. The included source
 * code can be redistributed and/or modified under the terms of the
 * GNU AFFERO GENERAL PUBLIC LICENSE Version 3
 * (http://www.fsf.org/licensing/licenses/agpl-3.0.html) with the
 * Commons Clause, as found in the associated LICENSE.txt file.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * Neo4j object code can be licensed independently from the source
 * under separate terms from the AGPL. Inquiries can be directed to:
 * licensing@neo4j.com
 *
 * More information is also available at:
 * https://neo4j.com/licensing/
 */
package org.neo4j.causalclustering.net;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;

import java.util.concurrent.ThreadFactory;

public abstract class EpollBootstrapConfig<CHANNEL extends Channel> implements BootstrapConfiguration<CHANNEL>
{
    public static EpollBootstrapConfig<EpollServerSocketChannel> epollServerConfig()
    {
        return new EpollBootstrapConfig<EpollServerSocketChannel>()
        {
            @Override
            public Class<EpollServerSocketChannel> channelClass()
            {
                return EpollServerSocketChannel.class;
            }
        };
    }

    public static EpollBootstrapConfig<EpollSocketChannel> epollClientConfig()
    {
        return new EpollBootstrapConfig<EpollSocketChannel>()
        {
            @Override
            public Class<EpollSocketChannel> channelClass()
            {
                return EpollSocketChannel.class;
            }
        };
    }

    @Override
    public EventLoopGroup eventLoopGroup( ThreadFactory threadFactory )
    {
        return new EpollEventLoopGroup( 0, threadFactory );
    }
}
