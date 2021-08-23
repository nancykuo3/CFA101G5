package com.store.model;

import java.util.List;
import java.util.Map;

//import com.page.model.PageBean;

public class StoreService {

	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreJDBCDAO();
	}

	public StoreVO addStore(String storeAcc, String storePwd, String storeName, Integer storeGui, String storePic,
			String storeTel, String storeFax, String storeAdd, String storePoc, String storeConPhone,
			String storeConAdd, String storeEmail, String bankAccount, Integer capacity, Byte dayOff, Integer startTime,
			Integer endTime) {

		StoreVO storeVO = new StoreVO();
		storeVO.setStoreAcc(storeAcc);
		storeVO.setStorePwd(storePwd);
		storeVO.setStoreName(storeName);
		storeVO.setStoreGui(storeGui);
		storeVO.setStorePic(storePic);
		storeVO.setStoreTel(storeTel);
		storeVO.setStoreFax(storeFax);
		storeVO.setStoreAdd(storeAdd);
		storeVO.setStorePoc(storePoc);
		storeVO.setStoreConPhone(storeConPhone);
		storeVO.setStoreConAdd(storeConAdd);
		storeVO.setStoreEmail(storeEmail);
		storeVO.setBankAccount(bankAccount);
		storeVO.setCapacity(capacity);
		storeVO.setDayOff(dayOff);
		storeVO.setStartTime(startTime);
		storeVO.setEndTime(endTime);

		storeVO = dao.insert(storeVO);

		return storeVO;
	}

	public void updateStore(StoreVO storeVO) {
		dao.update(storeVO);
	}

	public void updateStore(int storeId, Byte status) {
		dao.update_for_status(storeId, status);
	}

	public void deleteStore(Integer storeId) {
		dao.delete(storeId);
	}

	public StoreVO getOneStore(Integer storeId) {
		return dao.findByPrimaryKey(storeId);
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}

	public StoreVO getstore(String storeAcc, String storePwd) {
		return dao.getstore(storeAcc, storePwd);

	}

//	public StoreVO getstore(String storeAcc,String storePwd) {
//	return dao.getstore(storeAcc, storePwd);
//以下新增
//	public PageBean<StoreVO> findStoresByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
//		// new一個空的PageBean
//		PageBean<StoreVO> pb = new PageBean<StoreVO>();
//		// 設置參數
//		int currentPage = new Integer(_currentPage);
//		int rows = new Integer(_rows);
//		System.out.println("!!!");
//		System.out.println(_currentPage);
//
//		if (currentPage <= 0) {
//			currentPage = 1;
//		}
//
//		pb.setRows(rows);
//		// 查詢總資料筆數
//		int totalCount = dao.findStoreCount(condition);
//		pb.setTotalCount(totalCount);
//
//		// 查詢List集合
//		// 計算開始資料索引
//		int start = (currentPage - 1) * rows;
//		List<StoreVO> list = dao.findStoreByPage(start, rows, condition);
//		pb.setList(list);
//		int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;
//		if (currentPage > totalPage) {
//			currentPage = totalPage;
//		}
//		System.out.println(currentPage);
//		pb.setTotalPage(totalPage);
//		pb.setCurrentPage(currentPage);
//		return pb;
//	}

}
