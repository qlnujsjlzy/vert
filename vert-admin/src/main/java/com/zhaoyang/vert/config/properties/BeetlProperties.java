package com.zhaoyang.vert.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * beetl 配置
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Configuration
@ConfigurationProperties(prefix = BeetlProperties.BEETL_PROPERTIES_PREFIX)
@Setter
@Getter
public class BeetlProperties {

    static final String BEETL_PROPERTIES_PREFIX = "beetl";

    private String delimiterStatementStart;

    private String delimiterStatementEnd;

    private String resourceTagRoot;

    private String resourceTagSuffix;

    private String resourceAutoCheck;

    public Properties getProperties() {
        Properties properties = new Properties();
        if (StringUtils.isNotBlank(delimiterStatementStart)) {
            //yml 文件不允许@开头
            if (delimiterStatementStart.startsWith("\\")) {
                delimiterStatementStart = delimiterStatementStart.substring(1);
            }
            properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        }
        if (StringUtils.isNotBlank(delimiterStatementEnd)) {
            properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
        } else {
            properties.setProperty("DELIMITER_STATEMENT_END", "null");
        }
        if (StringUtils.isNotBlank(resourceTagRoot)) {
            properties.setProperty("RESOURCE.tagRoot", resourceTagRoot);
        }
        if (StringUtils.isNotBlank(resourceTagSuffix)) {
            properties.setProperty("RESOURCE.tagSuffix", resourceTagSuffix);
        }
        if (StringUtils.isNotBlank(resourceAutoCheck)) {
            properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
        }
        return properties;
    }
}
