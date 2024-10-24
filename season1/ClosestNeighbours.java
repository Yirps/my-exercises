class ClosestNeighbours {

    public static void main(String[] args) {

        int[] numbers = {0, 5, 1209, 6, 2, 111, 112, 33};
        int indexA = numbers[0];
        int indexB = numbers[1];
        int smallestNum = Math.abs(numbers[0] + numbers[1]);
        int numToCompare;

        for (int i = 1; i < numbers.length - 1; i++) {
            numToCompare = Math.abs(numbers[i] - numbers[i + 1]);
            if (numToCompare < smallestNum) {
                smallestNum = numToCompare;
                indexA = numbers[i];
                indexB = numbers[i + 1];
            }

        }


        System.out.println(indexA + " " + indexB);

    }
}
