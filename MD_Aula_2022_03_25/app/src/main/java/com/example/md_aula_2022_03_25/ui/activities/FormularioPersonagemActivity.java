package com.example.md_aula_2022_03_25.ui.activities;

import static com.example.md_aula_2022_03_25.ui.activities.ConstatesActivities.CHAVE_PERSONAGEM;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.md_aula_2022_03_25.R;
import com.example.md_aula_2022_03_25.dao.PersonagemDAO;
import com.example.md_aula_2022_03_25.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    // Declarar variaveis
    private  static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar o Persoangem";
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoNascimento;
    private EditText campoAltura;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    // Criar o menu referenciado do xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Ao selecionar o item do menu, verificar a id do item, se for o item de salvar o formulario do personagem, finalizar o formulario
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemID = item.getItemId();
        if (itemID == R.id.activity_formulario_personagem_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    // Criar a view de formulario do personagem e chamar os metodos de inicializacaoCampos e carregaPersonagem
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializacaoCampos();
        carregaPersonagem();
        //configuraBotaoSalvar();
        //checaPermissoes();
    }

    // Ao carregar o personagem verificar se ele já é um personagem existente ou é um novo
    // Se for um personagem existente, carregar os dados do personagem..
    // se não criar um novo personagem
    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)){
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    // Preencher os campos do personagem já existente
    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    //  Preencher os dados do personagem e se o perosnagem já existir, editar
    // se não salvar o personagem
    private void finalizarFormulario() {
        preencherPersonagem();
        if (personagem.IdValido()) {
            dao.edita(personagem);
            finish();
        } else {
            dao.salva(personagem);
        }
        finish();
    }

    //  Pegar os campos de texto que contem as informações do personagem
    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.editText_nome);
        campoNascimento = findViewById(R.id.editText_nascimento);
        campoAltura = findViewById(R.id.editText_altura);

        //SimpleMaskFormatter sfmAltura = new SimpleMaskFormatter("N", "NN");
        //MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        //campoAltura.addTextChangedListener(mtwAltura);

        //SimpleMaskFormatter sfmNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        //MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        //campoNascimento.addTextChangedListener(mtwNascimento);
    }

    // Prencheer os campos de texto com as informaçoes do personagem
    private void preencherPersonagem() {
        String nome = campoNome.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String altura = campoAltura.getText().toString();
    }
}
