import java.util.ArrayList;

public class BigInteger {
	final String value;
	boolean negative;
	final ArrayList<Integer> list = new ArrayList<Integer>();

	public BigInteger(String value) {
		negative = value.charAt(0) == '-';
		int i = negative ? 1 : 0;
		while (i < value.length()) {
			list.add(Integer.valueOf(String.valueOf(value.charAt(i))));
			i++;
		}
		this.value = value;
	}

	public static BigInteger operate(String operator, BigInteger operand1, BigInteger operand2) {
		if ("ADD".equals(operator)) {
			if (!operand1.negative && !operand2.negative)
				return operand1.add(operand2);
			else if (!operand1.negative && operand2.negative)
				return operand1.substract(operand2);
			else if (operand1.negative && !operand2.negative)
				return operand2.substract(operand1);
			else
				return operand1.add(operand2);
		} else if ("SUB".equals(operator)) {
			if (!operand1.negative && !operand2.negative)
				return operand1.substract(operand2);
			else if (!operand1.negative && operand2.negative)
				return operand1.add(operand2);
			else if (operand1.negative && !operand2.negative) {
				operand1.negative = operand2.negative = true;
				return operand1.add(operand2);
			}
			return operand2.substract(operand1);
		}
		return new BigInteger("0");

	}

	public BigInteger substract(BigInteger operand2) {
		ArrayList<Integer> list1 = this.list;
		ArrayList<Integer> list2 = operand2.list;
		ArrayList<Integer> result = new ArrayList<Integer>();
		int carry = 0;
		int i, j;
		boolean neg = false;
		if (list1.size() > list2.size()) {
			i = list1.size() - 1;
			j = list2.size() - 1;
		} else if (list1.size() < list2.size()) {
			j = list1.size() - 1;
			i = list2.size() - 1;
			neg = true;
		} else {
			i = list1.size() - 1;
			j = list2.size() - 1;
		}
		if (neg) {
			while (i >= 0 && j >= 0) {
				int sub = carry + list2.get(i) - list1.get(j);
				if (sub < 0) {
					result.add(10 + sub);
					carry = -1;
				} else {
					result.add(sub);
					carry = 0;
				}
				i--;
				j--;
			}
			while (i >= 0) {
				int sub = 0;
				sub = carry + list2.get(i);
				if (sub > 0) {
					result.add(sub);
					carry = 0;
				} else {
					result.add(10 + sub);
					carry = -1;
				}
				i--;
			}
		} else {
			while (i >= 0 && j >= 0) {
				int sub = carry + list1.get(i) - list2.get(j);
				if (sub < 0) {
					result.add(10 + sub);
					carry = -1;
				} else {
					result.add(sub);
					carry = 0;
				}
				i--;
				j--;
			}
			while (i >= 0) {
				int sub = 0;
				sub = carry + list1.get(i);
				if (sub > 0) {
					result.add(sub);
					carry = 0;
				} else {
					result.add(10 + sub);
					carry = -1;
				}
				i--;
			}
		}

		if (carry < 0)
			result.add(carry);

		StringBuilder sb = new StringBuilder();
		for (int k = result.size() - 1; k >= 0; k--) {
			if (k == result.size() - 1) {
				if (result.get(k) == -1) {
					neg = true;
					continue;
				} else
					sb.append(result.get(k));
			} else {
				sb.append(result.get(k));
			}
		}
		String res = sb.toString();
		if (neg)
			res = "-".concat(res);
		return new BigInteger(res);

	}

	public BigInteger add(BigInteger operand2) {
		ArrayList<Integer> list1 = this.list;
		ArrayList<Integer> list2 = operand2.list;
		ArrayList<Integer> result = new ArrayList<Integer>();
		int carry = 0;
		int i = list1.size() - 1, j = list2.size() - 1;
		while (i >= 0 && j >= 0) {
			int sum = carry + list1.get(i) + list2.get(j);
			if (sum >= 10) {
				result.add(sum % 10);
				carry = sum / 10;
			} else {
				result.add(sum);
				carry = 0;
			}
			i--;
			j--;
		}
		while (i >= 0) {
			result.add(list1.get(i));
			i--;
		}
		while (j >= 0) {
			result.add(list2.get(j));
			j--;
		}
		result.add(carry);

		StringBuilder sb = new StringBuilder();
		for (int k = result.size() - 1; k >= 0; k--) {
			if (k == result.size() - 1) {
				if (result.get(k) == 0)
					continue;
				else
					sb.append(result.get(k));
			} else {
				sb.append(result.get(k));
			}
		}
		String res = sb.toString();
		if (this.negative && operand2.negative)
			res = "-".concat(res);
		return new BigInteger(res);

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}

}
