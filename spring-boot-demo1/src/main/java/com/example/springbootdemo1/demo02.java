package com.example.springbootdemo1;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

import java.io.IOException;

public class demo02 {
    public static void main(String[] args) throws IOException {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\02.work_project\\09.锦江御味"); // 项目根目录
        config.setProjectName("crm"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\02.work_project\\66.other\\00.mydemo\\my_task"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
