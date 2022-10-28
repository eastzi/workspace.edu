package kr.bit.frontController;

public class ViewResolver {
	public static String makeView(String nextPage) {
		//prefix, subfix 수정이 필요할 때 여기서만 수정하면 전체 경로가 수정됨
		return "/WEB-INF/member/" + nextPage + ".jsp";
	}

}
