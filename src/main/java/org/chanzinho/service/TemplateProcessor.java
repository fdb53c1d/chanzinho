package org.chanzinho.service;

import java.io.File;
import java.util.Map;

import org.chanzinho.exception.ChanzinhoException;

public interface TemplateProcessor {
	
	void processTemplateFile(String templateFile, File outputFile, Map<String, Object> root) throws ChanzinhoException;

}
