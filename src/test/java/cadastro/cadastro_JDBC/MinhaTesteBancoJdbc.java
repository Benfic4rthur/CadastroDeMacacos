package cadastro.cadastro_JDBC;

import org.junit.Test;

import dao.MinhaDao;
import model.MinhaUserPosJava;

public class MinhaTesteBancoJdbc {
	private Long id;

	@Test
	public void initBanco() {
		MinhaDao minhaDao = new MinhaDao();
		id = (Long) null;
		MinhaUserPosJava minhaUserPosJava = new MinhaUserPosJava(id, null, null);
		
		minhaDao.salvar(minhaUserPosJava);
	}
}