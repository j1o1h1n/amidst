package amidst.mojangapi.file.nbt.playermover;

import java.io.FileNotFoundException;
import java.io.IOException;

import amidst.documentation.Immutable;
import amidst.mojangapi.file.directory.SaveDirectory;
import amidst.mojangapi.file.nbt.PlayerLocationSaver;
import amidst.mojangapi.world.player.Player;
import amidst.mojangapi.world.player.PlayerCoordinates;

@Immutable
public class MultiPlayerPlayerMover extends PlayerMover {
	private final SaveDirectory saveDirectory;

	public MultiPlayerPlayerMover(SaveDirectory saveDirectory) {
		this.saveDirectory = saveDirectory;
	}

	@Override
	protected boolean tryBackup(Player player) {
		return saveDirectory.tryBackupPlayersFile(player.getPlayerName());
	}

	@Override
	protected void doMovePlayer(Player player, PlayerCoordinates coordinates)
			throws FileNotFoundException, IOException {
		PlayerLocationSaver.writeToPlayerFile(coordinates,
				saveDirectory.getPlayersFile(player.getPlayerName()));
	}
}
