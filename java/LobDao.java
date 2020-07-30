package com.googosoft.dao.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.googosoft.dao.base.BaseDao;
import com.googosoft.util.UuidUtil;

/**  
* @Title: ApiTestDao.java  
* @Package com.test.dao.api  
* @Description: TODO
* @author wdm  
* @date 2018年9月11日  下午3:29:14
*/
@Repository
public class ApiTestDao extends BaseDao{
  @Autowired private JdbcTemplate jdbcTemplate;
	final LobHandler lobHandler=new DefaultLobHandler(); 
  
	public boolean UpdateQQ() throws FileNotFoundException {
		final File binaryFile=new File("C:\\222.jpg");  
		final File txtFile=new File("C:\\e.txt");  
		final InputStream is=new FileInputStream(binaryFile);  
		final Reader reader=new FileReader(txtFile);  
		int b = db.execute("INSERT INTO A_TEST(CS,DF,DF1) VALUES (?,?,?)",
				new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
					@Override
					protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
						ps.setString(1, UuidUtil.get32UUID());
						lobCreator.setClobAsCharacterStream(ps, 2, reader,(int)txtFile.length());
						lobCreator.setBlobAsBinaryStream(ps, 3, is,(int)binaryFile.length());
					}
				});
		if(b>0){
			return true;
		}else{
			return false;
		}
	}
  
	@SuppressWarnings({"rawtypes", "unchecked"})
	  public Object getData () throws IOException {
	  final List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
	  final Writer writer=new FileWriter("c:\\oracle_clob_back.txt");  
	  final OutputStream os=new FileOutputStream(new File("c:\\oracle_blob_back.jpg"));  
	  return (List<Map<String, Object>>)db.query("SELECT DF,DF1,CS FROM A_TEST WHERE CS='123456789000005'", new ResultSetExtractor() {
	    public Object extractData(ResultSet rs) throws SQLException,DataAccessException {
	      while(rs.next()){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("df", lobHandler.getClobAsString(rs, 1));
		map.put("df1", new String(lobHandler.getBlobAsBytes(rs, 2)));
		map.put("cs", rs.getString(3));
			   try {
			    FileCopyUtils.copy(lobHandler.getClobAsCharacterStream(rs,1),writer);  
		  FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs,2),os);
		} catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		}  
		list.add(map);
	      }
	      return list;
	    }
	  });
	  }
}
