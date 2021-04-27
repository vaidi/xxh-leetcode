//package com.example.xxhleetcode.netty;
//
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//import io.netty.handler.codec.http.websocketx.WebSocketFrame;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: elyuan
// * @Date: 2021/4/25 4:30 下午
// */
//@ChannelHandler.Sharable
//@Component
//public class WebsocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketMessageHandler.class);
//
//    @Autowired
//    private DiscardService discardService;
//
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
//        if(msg instanceof TextWebSocketFrame){
//            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame)msg;
//            discardService.discard(textWebSocketFrame.text());
//            ctx.channel().writeAndFlush(new TextWebSocketFrame("我收到了你的消息:"+System.currentTimeMillis()));
//        }else{
//            //ctx.channel().writeAndFlush(WebSocketCloseStatus.INVAL ID_MESSAGE_TYPE);
//            ctx.channel().writeAndFlush("我是谁");
//        }
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
//        LOGGER.info("链接断开:{}",ctx.channel().remoteAddress());
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        LOGGER.info("链接创建:{}",ctx.channel().remoteAddress());
//    }
//}
