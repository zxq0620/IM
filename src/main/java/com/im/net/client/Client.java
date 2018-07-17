package com.im.net.client;

import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;

public class Client  {

    private final String host;
    private final int port;

    public static void main(String[] args) {
        new Client("127.0.0.1",12345).run();
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel.class)            //3
                    //.remoteAddress(new InetSocketAddress(host, port))    //4
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//解决拆包粘包
                            ch.pipeline().addLast(new ProtobufDecoder(MessageOuterClass.Message.getDefaultInstance()));//解码器

                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());//编码器

                            ch.pipeline().addLast(new IdleStateHandler(0, 0, 300));
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture f = b.connect("127.0.0.1",12345).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            try {
                group.shutdownGracefully().sync();            //8
                System.out.println("group关闭了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}