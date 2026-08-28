package com.snooker.scorecalculator

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadDataWithBaseURL(null, """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body { font-family: sans-serif; text-align: center; background: #f0f0f0; padding: 20px; }
                        h2 { color: #333; }
                        .container { display: flex; justify-content: space-around; margin: 30px 0; }
                        .player { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 40%; }
                        .score { font-size: 48px; font-weight: bold; margin: 15px 0; color: #007bff; }
                        button { padding: 10px 20px; font-size: 16px; border: none; border-radius: 5px; cursor: pointer; }
                        .btn-add { background: #28a745; color: white; width: 100%; margin-bottom: 8px; }
                        .btn-sub { background: #dc3545; color: white; width: 100%; }
                        .btn-reset { background: #6c757d; color: white; padding: 12px 30px; font-size: 18px; }
                    </style>
                </head>
                <body>
                    <h2>Snooker Score Calculator</h2>
                    <div class="container">
                        <div class="player">
                            <h3>Player A</h3>
                            <div class="score" id="scoreA">0</div>
                            <button class="btn-add" onclick="change('A', 1)">+1 Point</button>
                            <button class="btn-sub" onclick="change('A', -1)">-1 Point</button>
                        </div>
                        <div class="player">
                            <h3>Player B</h3>
                            <div class="score" id="scoreB">0</div>
                            <button class="btn-add" onclick="change('B', 1)">+1 Point</button>
                            <button class="btn-sub" onclick="change('B', -1)">-1 Point</button>
                        </div>
                    </div>
                    <button class="btn-reset" onclick="resetMatch()">Reset Match</button>
                    <script>
                        let sA = 0, sB = 0;
                        function change(p, v) {
                            if(p === 'A') { sA = Math.max(0, sA + v); document.getElementById('scoreA').innerText = sA; }
                            else { sB = Math.max(0, sB + v); document.getElementById('scoreB').innerText = sB; }
                        }
                        function resetMatch() {
                            sA = 0; sB = 0;
                            document.getElementById('scoreA').innerText = 0;
                            document.getElementById('scoreB').innerText = 0;
                        }
                    </script>
                </body>
                </html>
            """, "text/html", "UTF-8", null)
        }
        setContentView(webView)
    }
}
