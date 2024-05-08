package hw3;

/**
 * 
 * @Project: 항공사 통합 예약 관리 시스템  
 * @프로그램 설명: 
 * 	- 다양한 예약 시스템 구현을 위한 인터페이스 설계 
 *
 * ReservationInterface.java
 *
 * @author Jung Changsu
 *
 */
public interface ReservationInterface {
	/**
	 * 좌석 행과 열의 수를 입력받아서 전체 좌석 배열을 생성하는 메소드 (makeSeats)
	 * @param row 좌석 행의 수 
	 * @param col 좌석 열의 수 
	 */
	public abstract void makeSeats(int row, int col);	 
	
	
	/**
	 * 좌석 예약 메소드 (reserveSeat)
	 * 
	 * @param seatName 좌석 이름: 1A, 1B 형태의 문자열   
	 * @return 
	 * 		true: 예약 성공 
	 * 		false: 예약 실패  
	 */
	public abstract boolean reserveSeat(String seatName); 
	
	
	/**
	 * 예약 취소 메소드 (cancelSeat)
	 * @param seatName 좌석 이름: 1A, 1B 형태의 문자열 
	 * @return
	 * 		 true: 예약 취소 성공 
	 * 		 false: 예약 실패 
	 */
	public abstract boolean cancelSeat(String seatName); 	
	
	
	/**
	 * 입력된 좌석의 유효성 검사 메소드 (getSeatIndex)
	 * 
	 * 입력된 문자열 형태의 좌석이 유효한지 검사하고 유효한 좌석인 경우
	 * row, col을 계산하여 seatArray[2] 배열에 저장
	 *  ex) seatName = 5A 인 경우, row=0, col=4
	 *      seatArray[0] = row 저장 
	 *      seatArray[1] = col 저장 
	 *      리턴: true
	 *      
	 *  ex) seatName = 11B 또는 1H 인 경우 
	 *      리턴: false
	 *      
	 * @param seatName
	 * @param seatArray
	 * @return true/false
	 */
	public boolean getSeatIndex(String seatName, int seatArray[]);
	
	
	/**
	 * 예약 현황 화면 출력 메소드 (displaySeat)
	 * @param airline
	 */
	public abstract void displaySeat(String airline);
}
