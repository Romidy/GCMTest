<?php

	$gcm = new GCM();

	// title : GCM通知のタイトル
	// message : 通知したいメッセージ
	$send_content = array("title"=> "GCMTest", "message"=> "send message!!");

	// 送信先端末
	// レジストレーションIDはGCMTestアプリで登録したidを画面かログからコピーする
	$registatoin_ids = array();
	array_push($registatoin_ids, 'eYBLJFrdZKQ:APA91bG8lIB_GoDuPL7PDUwwhe8eo_SbQPHIlXGhR2QIaSq2o211kv94dFmE1cet7mdhS7wrQ0Xf44XCYRG0OtuL7U70CjeIZbcXHppgacaPsKTs_p1rKyWhrZ3EhBsPeRm2nrG62L2o');

	$result_android = $gcm->send_notification($registatoin_ids, $send_content);

	class GCM{
		function __construct(){}

		public function send_notification($registatoin_ids, $send_content){

			// GOOGLE API KEY
			// https://console.developers.google.com/home/dashboard　から左メニュー→API Manager→認証情報で表示されるAPIキー
			define("GOOGLE_API_KEY","AIzaSyBXDLC56f5xxr_havdUZXGwDS3FUnm2mz4");
			$url = "https://android.googleapis.com/gcm/send";

			$fields = array(
				"collapse_key" => "score_update",
				"delay_while_idle" => true,
				"registration_ids" => $registatoin_ids,
				"data" => $send_content
			);

			$headers = array(
				"Authorization: key=".GOOGLE_API_KEY,
				"Content-Type: application/json"
			);

			$ch = curl_init();
			curl_setopt($ch,CURLOPT_URL,$url);
			curl_setopt($ch,CURLOPT_POST,true);
			curl_setopt($ch,CURLOPT_HTTPHEADER,$headers);
			curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
			curl_setopt($ch,CURLOPT_SSL_VERIFYPEER,false);
			curl_setopt($ch,CURLOPT_POSTFIELDS,json_encode($fields));

			// 環境毎のプロキシ設定
			curl_setopt($ch, CURLOPT_HTTPPROXYTUNNEL, TRUE);
			curl_setopt($ch, CURLOPT_PROXYPORT, '80');
			curl_setopt($ch, CURLOPT_PROXY, 'proxy-iti.iti.co.jp');

			$result = curl_exec($ch);
			if($result === FALSE){
				die("Curl failed: ".curl_error($ch));
			}

			curl_close($ch);
			echo $result;
		}
	}


?> 