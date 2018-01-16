package kq.qh.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * excel导出帮助注解类
 * @author Administrator
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

	String name();
	String value();
}
