package com.zhaoyang.vert.config.ext;

import com.zhaoyang.vert.core.shiro.ShiroExt;
import com.zhaoyang.vert.core.util.KaptchaUtil;
import com.zhaoyang.vert.core.util.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public class BeetlExtConfig extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    }
}
