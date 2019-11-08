package dao;

import java.util.List;

import model.Secretario;

public interface SecretarioDAO extends GenericDAO<Secretario> {
	public List<Secretario> findByNome(String nome);

	public List<Secretario> findByArea(String grau);

	public List<Secretario> findBySalario(double salario);
}