class ClosestNeighbours {

	public static void main(String[] args) {

                int[] numbers = {0, 5, 1209, 6, 2, 111, 112, 33};
		int[] differences = new int[numbers.length];
                
                for (int i = 1; i < numbers.length; i++) {

		  // Math.abs(differences[i] = numbers[i] - numbers[i - 1]);
                  numbers[i] = numbers[i] - numbers[i - 1];
		  Math.abs(numbers[i]);
		     

		}

	



		System.out.println(numbers);

	}
}
