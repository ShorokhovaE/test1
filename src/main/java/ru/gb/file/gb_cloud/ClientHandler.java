package ru.gb.file.gb_cloud;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.gb.file.gb_cloud.dto.BasicResponse;
import ru.gb.file.gb_cloud.dto.LoadFileRequest;
import java.io.FileOutputStream;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        BasicResponse response = (BasicResponse) msg;
        System.out.println(response.getResponse());
        String responseText = response.getResponse();

        if("login_ok".equals(responseText)){
            PrimaryController pr =
                    (PrimaryController) ControllerRegistry.getControllerObject(PrimaryController.class);
            pr.loginOk();
        } if("login_no".equals(responseText)){
            PrimaryController pr =
                    (PrimaryController) ControllerRegistry.getControllerObject(PrimaryController.class);
            pr.loginNo();
        } if("reg_no".equals(responseText)){
            PrimaryController pr =
                    (PrimaryController) ControllerRegistry.getControllerObject(PrimaryController.class);
            pr.regNo();
        }if("log_off".equals(responseText)){
            System.out.println("Клиент вышел");
        } if("download_ok".equals(responseText)) {
            System.out.println("Скачивание файла");
            String pathOfFile = String.format("./%", response.getFile().getName());
            FileOutputStream fos = new FileOutputStream(pathOfFile);
            fos.write((response).getData());
        }

//        if ("file list....".equals(responseText)) {
//            ctx.close();
//            return;
//        }
//        ctx.writeAndFlush(new GetFileListRequest());
    }


}
