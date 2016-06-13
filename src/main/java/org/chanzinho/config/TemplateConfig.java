package org.chanzinho.config;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateExceptionHandler;

@Configuration
public class TemplateConfig {

	private freemarker.template.Configuration cfg;
	
	@PostConstruct
	public void init() {
		try{
			cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			cfg.setAPIBuiltinEnabled(true);
		} catch(IOException e) {
			
		}
	}
	
	public freemarker.template.Configuration getConfig() {
		return cfg;
	}
}
