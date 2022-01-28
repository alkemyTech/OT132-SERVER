package com.alkemy.ong.common.mail;

import java.lang.reflect.Field;

public interface IMail {

  String getSubject();

  IContent getContent();

  String getTo();
}
