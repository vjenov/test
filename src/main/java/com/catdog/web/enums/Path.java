package com.catdog.web.enums;

public enum Path {
	BUGS, CGV, DICTION;
	public String toString() {
		String result = "";
		switch(this) {
		case BUGS :
			result = "https://music.bugs.co.kr/chart";
			break;
		case CGV :
			result = "http://www.cgv.co.kr/movies/?lt=3";
			break;
		case DICTION :
			result = "https://endic.naver.com/?sLn=kr";
			break;
		}
		return result;
	}
}
