package com.laboratory.system.role.Controller;

import com.laboratory.system.role.model.Role;
import com.laboratory.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * role controller
 * Created by Lpan on 2017/12/21.
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     *  添加
     * @param model
     */
    @RequestMapping("/add")
    public void insert(Role model){
         roleService.insert(model);
    }
    /**
     * 根据id 删除
     * @param id
     */
    @RequestMapping("/deleteByid")
    public  void delete(Integer id){
        roleService.deleteByPrimaryKey(id);
    }
    /**
     *    根据id 修改激活状态
      * @param id
     */
    @RequestMapping("/updateStatus")
    public void updateStatus(Integer id){
        Map<String,String> param = new HashMap<String, String>();
        roleService.updateStatus(param);
    }


    /**
     *根据主键查询
     * @mbg.generated Tue Dec 12 16:55:30 CST 2017
     */
    @RequestMapping("/selectById")
    public Role selectById(Integer id){
        return roleService.selectByPrimaryKey(id);
    }

    /**
     *  查询分页查询
     * @mbg.generated Tue Dec 12 16:55:30 CST 2017
     */
    @RequestMapping("/selectByPage")
    public List<Role> selectAllByParam(Map<String,String> param){
        return roleService.selectAllByParam(param);
    }

    /**
     *  更新
     * @mbg.generated Tue Dec 12 16:55:30 CST 2017
     */
    @RequestMapping("/updateRole")
    public int updateRole(Role role){
        return roleService.updateByPrimaryKey(role);
    }

}
