package com;

import java.util.ArrayList;

public class Generator {

	private int currentIndex = -1;
	private ArrayList<Character> symbols;
	private Generator next;

	public Generator(ArrayList<Character> symbols, Generator gen) {
		this.symbols = symbols;
		this.next = gen;
	}

	public void next() {
		currentIndex++;
		if (currentIndex == symbols.size()) {
			currentIndex = 0;
			if (next != null) {
				next.next();
			}
		}
	}

	public void getKey(StringBuilder generateKey) {
		if (next != null) {
			next.getKey(generateKey);
		}
		if (currentIndex > -1) {
			generateKey.append(symbols.get(currentIndex));
		}
	}
}
