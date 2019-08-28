package microservices.book.multiplication.response;

public class ResultResponse {

	private boolean correct;
	
	public ResultResponse() {
	}

	public boolean isCorrect() {
		return correct;
	}

	public ResultResponse(boolean correct) {
		this.correct = correct;
	}

	
}
