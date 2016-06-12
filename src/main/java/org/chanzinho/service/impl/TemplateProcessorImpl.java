package org.chanzinho.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.chanzinho.config.TemplateConfig;
import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.service.TemplateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class TemplateProcessorImpl implements TemplateProcessor {
	
	@Autowired
	TemplateConfig templateConfig;
	
	@Override
	public void processTemplateFile(String templateFile, File outputFile, Map<String, Object> root) throws ChanzinhoException {
		
		try{
			Configuration cfg = templateConfig.getConfig();
			Template tmp = cfg.getTemplate(templateFile);
			OutputStream stream = new FileOutputStream(outputFile);
			Writer out = new OutputStreamWriter(stream);
			
			tmp.process(root, out);
			out.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new ChanzinhoException("Erro ao processar template, arquivo nao encontrado.");
		} catch(IOException e) {
			e.printStackTrace();
			throw new ChanzinhoException("Erro de leitura/escrita ao processar template.");
		} catch(TemplateException e) {
			e.printStackTrace();
			throw new ChanzinhoException("Erro ao processar template.");
		}
	}
}