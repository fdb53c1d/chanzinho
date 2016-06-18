package org.chanzinho.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

public class ServerConfig extends ServerProperties {

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    super.customize(container);
    container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/templates/404.html"));
  }

}
