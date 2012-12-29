package jp.ikedam.jenkins.plugins.setenv_builder;

import java.util.Map;
import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.EnvironmentContributingAction;

/**
 * ビルド環境に環境変数を追加するアクション
 */
public class SetenvContributingAction implements EnvironmentContributingAction
{
    /**
     * 設定する変数
     */
    private Map<String, String> appendEnvMap;
    
    public SetenvContributingAction(Map<String, String> appendEnvMap)
    {
        this.appendEnvMap = appendEnvMap;
    }
    
    /**
     * ビルド前に呼び出される処理。変数の追加処理を行う。
     */
    @Override
    public void buildEnvVars(AbstractBuild<?,?> build, EnvVars env)
    {
        env.putAll(appendEnvMap);
    }
    
    /**
     * このアクションの名称。表示の対象にしないので、実際には使用されない。
     */
    @Override
    public String getDisplayName()
    {
        return Messages._SetenvContributingAction_DisplayName().toString();
    }
    
    /**
     * ビルド履歴のメニューに表示するときのアイコン。表示の対象にしないので、nullを返す。
     */
    @Override
    public String getIconFileName()
    {
        return null;
    }
    
    /**
     * ビルド履歴のメニューに表示するときのリンク先。表示の対象にしないので、nullを返す。
     */
    @Override
    public String getUrlName()
    {
        return null;
    }
}
