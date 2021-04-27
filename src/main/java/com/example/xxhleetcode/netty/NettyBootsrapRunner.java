//package com.example.xxhleetcode.netty;
//
//import com.sun.net.httpserver.HttpServer;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.sctp.nio.NioSctpServerChannel;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
//import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
//import io.netty.handler.stream.ChunkedWriteHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextClosedEvent;
//import org.springframework.stereotype.Component;
//
//import java.net.InetSocketAddress;
//
///**
// * @Author: elyuan
// * @Date: 2021/4/25 3:57 下午
// */
//@Component
//public class NettyBootsrapRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(NettyBootsrapRunner.class);
//    @Value("${netty.websocket.port}")
//    private int port;
//
//    @Value("${netty.websocket.ip}")
//    private String ip;
//
//    @Value("${netty.websocket.path}")
//    private String path;
//
//    @Value("${netty.websocket.max-frame-size}")
//    private int maxFrameSize;
//
//    private ApplicationContext applicationContext;
//
//    private Channel serverChannel;
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup worketGroup = new NioEventLoopGroup();
//        try{
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(bossGroup, worketGroup);
//            serverBootstrap.channel(NioSctpServerChannel.class);
//            serverBootstrap.localAddress(new InetSocketAddress(this.ip,this.port));
//            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    ChannelPipeline pipeline = socketChannel.pipeline();
//                    pipeline.addLast(new HttpServerCodec());
//                    pipeline.addLast(new ChunkedWriteHandler());
//                    pipeline.addLast(new HttpObjectAggregator(65536));
//                    pipeline.addLast(new ChannelInboundHandlerAdapter(){
//
//                        @Override
//                        public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
//                            if(msg instanceof FullHttpRequest){
//                                FullHttpRequest fullHttpRequest =  (FullHttpRequest)msg;
//                                String uri =  fullHttpRequest.uri();
//                                if(!uri.equals(path)){
//                                    ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,HttpResponseStatus.NOT_FOUND))
//                                            .addListener(ChannelFutureListener.CLOSE);
//                                    return;
//                                }
//                            }
//                            super.channelRead(ctx,msg);
//                        }
//                    });
//                    pipeline.addLast(new WebSocketServerCompressionHandler());
//                    pipeline.addLast(new WebSocketServerProtocolHandler(path,null,true,maxFrameSize));
//
//                   pipeline.addLast(applicationContext.getBean(WebsocketMessageHandler.class));
//
//
//
//
//
//                }
//            });
//            Channel channel = serverBootstrap.bind().sync().channel();
//            this.serverChannel = channel;
//            LOGGER.info("websocket 服务启动，ip={},port={}", this.ip, this.port);
//            channel.closeFuture().sync();
//        }finally {
//            bossGroup.shutdownGracefully();
//            worketGroup.shutdownGracefully();
//        }
//    }
//
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//
//    }
//
//    @Override
//    public void onApplicationEvent(ContextClosedEvent event) {
//        if(this.serverChannel != null){
//            this.serverChannel.close();
//        }
//        LOGGER.info("websocket 服务停止");
//    }
//}
