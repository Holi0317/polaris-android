package agersant.polaris;

import android.content.Context;

import agersant.polaris.api.API;
import agersant.polaris.api.local.LocalAPI;
import agersant.polaris.api.local.OfflineCache;
import agersant.polaris.api.remote.DownloadQueue;
import agersant.polaris.api.remote.ServerAPI;

public class PolarisState {

	public final OfflineCache offlineCache;
	public final DownloadQueue downloadQueue;
	public final PlaybackQueue playbackQueue;
	public final PolarisPlayer player;
	public final ServerAPI serverAPI;
	public final LocalAPI localAPI;
	public final API api;


	public PolarisState(Context context) {
		serverAPI = new ServerAPI(context);
		localAPI = new LocalAPI();
		api = new API(context);
		player = new PolarisPlayer(context, api);
		playbackQueue = new PlaybackQueue();
		offlineCache = new OfflineCache(context, playbackQueue, player);
		downloadQueue = new DownloadQueue(context, api, playbackQueue, player, offlineCache, serverAPI);

		serverAPI.initialize(downloadQueue);
		localAPI.initialize(offlineCache);
		api.initialize(offlineCache, serverAPI, localAPI);
	}

}
