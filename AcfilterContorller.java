/*
 * Powered By [JHOP3.0-AutoCode]
 * Web Site: http://www.techstar.com
 * Since  2014-09-11 
 */

package com.techstar.jyy.platform.filter.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.techstar.jyy.platform.filter.entity.AcFilter;
import com.techstar.jyy.platform.filter.service.AcFilterService;
import com.techstar.jyy.platform.reactive.controller.BaseContorller;
import com.techstar.modules.jackson.mapper.JsonMapper;
import com.techstar.modules.springframework.data.jpa.domain.PageResponse;
import com.techstar.modules.springframework.data.jpa.domain.Results;
import com.techstar.modules.springframework.web.bind.annotation.PreModelAttribute;

/**
 * @author JHOP3.0-AutoCode
 * @since   2014-09-11 10:31 
 */

/***
 * 滤波器信息-基于Spring MVC的控制层
 * @Date   2014-09-11 10:31 
 * @author JHOP-AutoCode
 */
@Controller
@RequestMapping("/acfilter/acFilter")
public class AcfilterContorller extends BaseContorller
{
    
    private static Logger logger = LoggerFactory.getLogger(AcfilterContorller.class);

	private static final JsonMapper JSONMAPPER = JsonMapper.nonEmptyMapper();

    @Autowired
    private AcFilterService acFilterService;

   

    /***
     * 查询滤波器信息
     * @Date   2014-09-11 10:31 
     * @author JHOP-AutoCode
     */
    //@RequiresPermissions(value = { "admin", "AcFilter:read" }, logical = Logical.OR)
    @RequestMapping("/search")
    public @ResponseBody Results search(@PageableDefaults(pageNumber = 0, value = 10) Pageable pageable, Specification<AcFilter> spc,
    		 @RequestParam(value = "name", required = false) String name
    		 ,@RequestParam(value = "type", required = false) String type) {
    	List<AcFilter> acFilterList = new ArrayList<AcFilter>();
        String receivestr="";
    	if(type.equals("0")){
    		acFilterList=null;
    	}if(type.equals("2")){
    	//if (receivestr.toUpperCase().indexOf("OK") > -1) {
          acFilterList=acFilterService.getAcFilterList(this.getCurrentProjectUsernamePB());
    	}
    	else{
//    		try {
//        		receivestr=UdpSocketService.sendAcfilterOrder("0@" + this.getCurrentProjectUsername()
//        				+ "@status@tf_filter_calcu:" +name
//        				+ "@acfilter@read_perf@null");
//        	} catch (IOException e) {
//        		e.printStackTrace();
//        	}
    	//if (receivestr.toUpperCase().indexOf("OK") > -1) {
        //  acFilterList=acFilterService.getAcFilterList(this.getCurrentProjectUsername());
//      	} else {
    		//acFilterList=null;
//  	}
    		acFilterList=null;
    	}
	return new PageResponse<AcFilter>(acFilterList);
    }
    /***
     * 打开详情页
     * @Date   2014-09-11 10:31 
     * @author JHOP-AutoCode
     */
    @RequestMapping(value = "/initDetail")
	public String initDetail(Model model, @PreModelAttribute(value = "id", preparable = true) AcFilter AcFilter) {
		model.addAttribute("AcFilter", AcFilter);
		return "AcFilter/AcFilterDetail";
	}

    
}
