package br.edu.ifsp.scl.calculadorasdmkt.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ConfiguracaoService(context: Context) {
    // Constantes
    companion object{
        val NOME_ARQUIVO = "configuracoes"
        val MODO_ARQUIVO = Context.MODE_PRIVATE
        val TAG_CONFIGURACAO = "configuracoes"
    }

    val sharedPreferences: SharedPreferences
    val gson: Gson
    init{
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO, MODO_ARQUIVO)
        gson = GsonBuilder().create()
    }

    fun setConfiguracao(configuracao: Configuracao) {
        val configuracaoJson = JSONObject(gson.toJson(configuracao))

        val spEditor: SharedPreferences.Editor = sharedPreferences.edit()
        spEditor.putString(TAG_CONFIGURACAO, configuracaoJson.toString())
        spEditor.commit()
    }

    fun getConfiguracao(): Configuracao {
        val configuracaoString = sharedPreferences.getString(TAG_CONFIGURACAO, "")

        return if (configuracaoString != "")
            gson.fromJson(configuracaoString, Configuracao::class.java)
        else
            Configuracao()
    }
}