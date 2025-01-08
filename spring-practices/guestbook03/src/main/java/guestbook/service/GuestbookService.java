package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import guestbook.repository.GuestbookLogRepository;
import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private PlatformTransactionManager transactionManager;

	private final GuestbookRepository guestbookRepository;
	private final GuestbookLogRepository guestbookLogRepository;
	private final DataSource dataSource;

	public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository,
			DataSource dataSource) {
		this.guestbookRepository = guestbookRepository;
		this.guestbookLogRepository = guestbookLogRepository;
		this.dataSource = dataSource;
	}

	public List<GuestbookVo> findAll() {
		return guestbookRepository.findAll();
	}

	public void deleteContents(Long id, String password) {
		/* Transaction Begin */
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		GuestbookVo vo = guestbookRepository.findById(id);

		try {
			if (vo == null) {
				return;
			}

			int count = guestbookRepository.deleteByIdAndPassword(id, password);
			if (count == 1) {
				guestbookLogRepository.update(vo.getRegDate());
			}

			/* Transaction End(Success) */
			transactionManager.commit(txStatus);

		} catch (Throwable e) {
			/* Transaction End(Fail) */
			transactionManager.rollback(txStatus);
			
			throw new RuntimeException(e);
		}
	}

	public void addContents(GuestbookVo vo) {
		// 트랜잭션 동기(Connection) 초기화
		TransactionSynchronizationManager.initSynchronization();
		Connection conn = DataSourceUtils.getConnection(dataSource);

		try {
			/* Transaction Begin */
			conn.setAutoCommit(false);

			int count = guestbookLogRepository.update();
			if (count == 0) {
				guestbookLogRepository.insert();
			}

			guestbookRepository.insert(vo);
			/* Transaction End(Success) */

			conn.commit();

		} catch (Throwable e) {
			/* Transaction End(Fail) */
			try {
				conn.rollback();

			} catch (SQLException ignore) {
			}

			throw new RuntimeException(e);

		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}

	}
}
