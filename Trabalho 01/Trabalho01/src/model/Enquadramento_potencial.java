package model;

public class Enquadramento_potencial {
	private Potencial potencial;

	public Enquadramento_potencial(Potencial potencial) {
		this.potencial = potencial;
	}

	public Enquadramento_potencial() {}

	public Potencial getPotencial() {
		return this.potencial;
	}

	public void setPotencial(Potencial potencial) {
		this.potencial = potencial;
	}
}