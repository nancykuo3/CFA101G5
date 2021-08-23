package com.mem.model;

import java.util.List;

import com.pic.model.PicVO;


public interface MemDAO_interface {
        public MemVO insert(MemVO memVO);
        public void update(MemVO memVO);
        public void update_for_status(int memId , Byte status);
        public void delete(Integer memId);
        public MemVO findByPK(Integer memid);
        public List<MemVO> getAll();
        public MemVO getmem(String memAcc,String memPwd);
        
     // 修改圖片
    	public MemVO getOneUpdate(MemVO memVO);
		List<PicVO> getPicFromOneMem(Integer memId);
		public void updatenopic(MemVO memVO);
    }
		

