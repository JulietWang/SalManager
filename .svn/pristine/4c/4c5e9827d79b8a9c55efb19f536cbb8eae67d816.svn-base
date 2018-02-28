package com.guigu.salmanager.main;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.guigu.salmanager.ui.LoginJFrame;

public class EntranceUI {

	public static void main(String[] args) {
		// 设置窗口的边框样式
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new LoginJFrame();
	}

}
