package com.alkemy.ong.common.mail;

public interface IMail {

  String getSubject();

  IContent getContent();

  String getTo();

}
