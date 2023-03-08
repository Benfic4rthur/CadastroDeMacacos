package cadastro.cadastro_JDBC;

import org.junit.Test;

import dao.MinhaDao;
import model.MinhaUserPosJava;

public class MinhaTesteBancoJdbc {
	@Test
	public void initBanco() {
		MinhaDao minhaDao = new MinhaDao();
		MinhaUserPosJava minhaUserPosJava = new MinhaUserPosJava(null, null, null);
		
		minhaDao.salvar(minhaUserPosJava);
	}
}