package com.dolgy.play.grizzly.server.config;

import com.sun.jersey.api.core.PackagesResourceConfig;

public class RestApplication extends PackagesResourceConfig {
	  public RestApplication() {
	    super("com.dolgy.play.grizzly.server.resources");
	  }
	}
