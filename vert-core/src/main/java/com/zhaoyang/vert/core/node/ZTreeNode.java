package com.zhaoyang.vert.core.node;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * jquery ztree 插件的节点
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Setter
@Getter
@ToString
public class ZTreeNode {

    /**
     * 节点id
     */
    private Long id;
    /**
     * 父节点id
     */
    private Long pId;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 是否打开节点
     */
    private Boolean open;
    /**
     * 是否被选中
     */
    private Boolean checked;


    public static ZTreeNode createParent() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setPId(0L);
        return zTreeNode;
    }
}
