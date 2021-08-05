package com.spring.mvc.board.commons;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이지 알고리즘에 의해 이전, 다음 버튼 및 페이지 버튼 개수 및 번호를 관장할 객체
@Getter
@Setter
@ToString
public class PageCreator {

	//사용자가 선택한 페이지 번호, 게시물의 개수를 갖고있는 객체
	private PageVO paging;
	private int articleTotalCount; //총 게시물의 수
	private int beginPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호
	private boolean prev; //이전 버튼
	private boolean next; //다음 버튼
	
	//한 화면에 보여질 페이지 버튼 수
	private final int displayPageNum = 10;
	
	//URI 파라미터를 쉽게 만들어 주는 유틸 메서드
	public String makeURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance().queryParam("page", page)
															  .queryParam("countPerPage", paging.getCountPerPage())
															  .queryParam("keyword", ((SearchVO)paging).getKeyword())
															  .queryParam("condition", ((SearchVO)paging).getCondition())
															  .build();
		return ucp.toUriString();
	}
	
	
	//페이징 알고리즘을 수행할 메서드 선언.
	private void calcDataOfPage() {
		
		//보정 전 끝 페이지 번호 구하기
		endPage = (int) (Math.ceil(paging.getPage() / (double) displayPageNum) * displayPageNum);
		
		//시작 페이지 번호 구하기
		beginPage = (endPage - displayPageNum) + 1;
		
		//이전 버튼 활성 여부
		prev = (beginPage == 1) ? false : true;
		
		//다음 버튼 활성 여부
		next = articleTotalCount <= (endPage * paging.getCountPerPage()) ? false : true;
		
		//끝 페이지 보정
		if(!isNext()) {
			endPage = (int) Math.ceil(articleTotalCount / (double) paging.getCountPerPage());
		}
		
	}
	
	public void setPaging(PageVO paging) {
		this.paging = paging;
	}
	
	public void setArticleTotalCount(int articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}
	
}














