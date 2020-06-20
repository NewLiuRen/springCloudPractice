package com.demo.service;

import com.demo.util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

@Service
public class GoodsHtmlService {
    @Autowired
    private TemplateEngine engine;
    @Autowired
    private GoodsService goodsService;

    public void createHtml(Long spuId) {

        PrintWriter printWriter = null;
        try {
            Context context = new Context();

            Map<String, Object> map = this.goodsService.loadModel(spuId);
            context.setVariables(map);

            File file = new File("E:\\WEB-Plugin\\nginx-1.16.1\\html\\item\\" + spuId + ".html");
            printWriter = new PrintWriter(file);

            engine.process("item", context, printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) printWriter.close();
        }
    }

    public void asyncExcute(Long spuId) {
        ThreadUtil.execute(() -> this.createHtml(spuId));
    }

    public void deleteHtml(Long id) {
        File file = new File("E:\\WEB-Plugin\\nginx-1.16.1\\html\\item\\" + id + ".html");
        file.deleteOnExit();
    }
}
