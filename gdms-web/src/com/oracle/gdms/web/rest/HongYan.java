package com.oracle.gdms.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import jdk.nashorn.api.scripting.JSObject;

@Path("/hongyan")
public class HongYan {
	
	@Path("/sing")
	@GET
	public String sing() {
		System.out.println("���޳��������");
		return "hello";
	}

	@Path("/sing/{name}")
	@GET
public String sing(@PathParam("name") String name) {
	System.out.println("����="+ name);
	return "ok";
}


    @Path("/push/one")
    @POST
public String push(@FormParam("name") String name) {
   System.out.println("��Ʒ����="+ name);
   return "form";
   }
    
    @Path("/push/json")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String pushJson(String jsonparm) {
    	   System.out.println(jsonparm);
    	   JSONObject j = JSONObject.parseObject(jsonparm);
    	   String name = j.getString("name");
    	   String sex = j.getString("sex");
    	   String age = j.getString("age");
    	   System.out.println("name:"+name);
    	   System.out.println("sex:"+sex);
    	   System.out.println("age:"+age);
    	   return "json";
    	   }
    
    @Path("/goods/update/type")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)//�涨���صĽ����json����
    public String updateGoodsType(String jsonparam) {
    	System.out.println("str="+jsonparam);
    	JSONObject j = JSONObject.parseObject(jsonparam);
    	String goodsid = j.getString("goodsid");
    	String gtid = j.getString("gtid");
   //	System.out.println("Ҫ�޸ĵĲ�Ʒid" + goodsid +"Ŀ�����id=" +gtid);
    //	Goodsid  id = new Goodsid();
    	GoodsEntity goods = new GoodsEntity();//����һ��ʵ����
    	goods.setGoodsid(Integer.parseInt(goodsid));
    	goods.setGtid(Integer.parseInt(gtid));
    	GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
    	int count = service.goodsUpdateType(goods);//����һ���������أ����򴴽�����
    	return j.toJSONString();
    }
    
    @Path("/goods/push/bytype")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<GoodsModel> findByType(GoodsType type){
    	List<GoodsModel> list = null;
    	GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
    	list = service.findByType(type.getGtid());
    	System.out.println("size=" + list.size());
    	return list;
    }
    
    
    @Path("/goods/push/one")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity pushGoodsOne(String jsonparam) {
    	//{"goods" :{"goodsid":42,"name":"��Ȫˮ","price":"3.5"}}
    	    	ResponseEntity r = new ResponseEntity();
    	try {
    		JSONObject j = JSONObject.parseObject(jsonparam);
        	String gs = j.getString("goods");
        	GoodsModel goodsModel = JSONObject.parseObject(gs,GoodsModel.class);
        	System.out.println("������յ���");
        	System.out.println("��Ʒid" + goodsModel.getGoodsid());
        	System.out.println("��Ʒ����"+goodsModel.getName());

    		
			r.setCode(0);
			r.setMessage("���ͳɹ�");
			//
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			r.setCode(1184);
			r.setMessage("����ʧ��"+jsonparam);
		}
    	return r;
    }
}
