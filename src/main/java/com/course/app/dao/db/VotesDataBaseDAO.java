package com.course.app.dao.db;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class VotesDataBaseDAO implements IVotesDAO {

	private IDataSourceWrapper dataSource;

	private final static String GET_VOTES_WITH_GENRES = "SELECT app.vote.id AS vote_id, app.genre.id AS genre_id, app.genre.name AS genre_name " +
			"FROM app.vote INNER JOIN app.vote_genre ON app.vote.id = app.vote_genre.vote_id " +
			"INNER JOIN app.genre ON app.vote_genre.genre_id = app.genre.id;" ;

	private final static String GET_VOTES_WITH_ARTISTS = "SELECT app.vote.id AS vote_id, app.artist.id AS artist_id, app.artist.name AS artist_name, " +
			"app.vote.about, app.vote.dtcreate " +
			"FROM app.artist INNER JOIN app.vote_artist ON app.artist.id = app.vote_artist.artist_id " +
			"INNER JOIN app.vote ON app.vote_artist.vote_id = app.vote.id;";

	private final static String INSERT_INTO_VOTES = "INSERT INTO app.vote (dtcreate, about) VALUES (NOW(), ?);";

	private final static String INSERT_INTO_VOTE_ARTIST = "INSERT INTO app.vote_artist(vote_id, artist_id) VALUES (?, ?);";

	private final static String INSERT_INTO_VOTE_GENRE = "INSERT INTO app.vote_genre(vote_id, genre_id) VALUES (?, ?);";

	public VotesDataBaseDAO(IDataSourceWrapper wrapper){
		this.dataSource = wrapper;
	}

	@Override
	public List<VoteDTO> getAll() {
		Map<Long, Set<GenreDTO>> votesGenresMap = getVotesGenresMap();
		List<VoteDTO> votes = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_VOTES_WITH_ARTISTS);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				Long vote_id = resSet.getLong("vote_id");
				Long artist_id = resSet.getLong("artist_id");
				String artist_name = resSet.getString("artist_name");
				String text = resSet.getString("about");
				LocalDateTime dtCreate = convertToLocalDateTime(resSet.getDate("dtcreate"));
				VoteDTO dto = new VoteDTO(vote_id,
						new ArtistDTO(artist_id, artist_name),
						votesGenresMap.get(vote_id),
						dtCreate,
						text);
				votes.add(dto);
			}
			return votes;
		}catch (SQLException e){
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(VoteDTO vote) {
		try(Connection connection = dataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_VOTES, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, vote.getAbout());
			stmt.executeUpdate();
			ResultSet resSetId = stmt.getGeneratedKeys();
			resSetId.next();
			Long newId = resSetId.getLong(1);
			saveVoteArtist(newId, vote);
			saveVoteGenre(newId, vote);

		}catch (SQLException e) {
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	private Map<Long, Set<GenreDTO>> getVotesGenresMap() {
		Map<Long, Set<GenreDTO>> votesGenresMap = new HashMap<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_VOTES_WITH_GENRES);
		    ResultSet resSet = stmt.executeQuery();) {

			while(resSet.next()) {
				if(!votesGenresMap.containsKey(resSet.getLong("vote_id"))) {
					votesGenresMap.put(resSet.getLong("vote_id"), new HashSet<>());
					votesGenresMap.get(resSet.getLong("vote_id")).add(new GenreDTO(resSet.getLong("genre_id"),
							resSet.getString("genre_name")));

				}else {
					votesGenresMap.get(resSet.getLong("vote_id")).add(new GenreDTO(resSet.getLong("genre_id"),
							resSet.getString("genre_name")));
				}
			}

			return votesGenresMap;

		}catch (SQLException e) {
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	private LocalDateTime convertToLocalDateTime(Date toConvert) {
		Timestamp timestamp = new Timestamp(toConvert.getTime());
		return timestamp.toLocalDateTime();
	}

	private void saveVoteGenre(Long id, VoteDTO dto) {
		try(Connection connection = dataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_VOTE_GENRE)) {

			Long[] genre_ids = dto.getGenresId();
			for(Long item : genre_ids) {
				stmt.setLong(1, id);
				stmt.setLong(2, item);
				stmt.executeUpdate();
			}
		}catch (SQLException e) {
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	private void saveVoteArtist(Long id, VoteDTO dto) {
		try(Connection connection = dataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_VOTE_ARTIST)){

			stmt.setLong(1, id);
			stmt.setLong(2, dto.getArtistId());
			stmt.executeUpdate();

		}catch (SQLException e) {
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}
}
