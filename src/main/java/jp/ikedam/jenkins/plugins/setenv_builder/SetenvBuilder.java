package jp.ikedam.jenkins.plugins.setenv_builder;

import java.util.Map;
import hudson.Extension;
import hudson.Launcher;
import hudson.DescriptorExtensionList;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Descriptor;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * 環境変数を設定し、それ以降のビルドステップで使えるようにするプラグイン
 */
public class SetenvBuilder extends Builder {
    /**
     * 設定する環境変数の一覧を提供するモジュール
     * Extension Pointで幾つかのモジュールを切り替えれる。
     */
    private SetenvConfig setenvConfig = null;
    
    public SetenvConfig getSetenvConfig()
    {
        return setenvConfig;
    }
    
    /**
     * 入力された設定から新規にこのオブジェクトを構築するときに使用するコンストラクタ
     */
    @DataBoundConstructor
    public SetenvBuilder(SetenvConfig setenvConfig)
    {
        this.setenvConfig = setenvConfig;
    }
    
    /**
     * ビルドの実行
     */
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
        throws IOException, InterruptedException
    {
        Map<String,String> env = setenvConfig.getEnvMap(build.getEnvironment(listener), listener.getLogger());
        
        build.addAction(new SetenvContributingAction(env));
        
        return true;
    }
    
    /**
     * ビューとの対応付けを行うための定義。
     * Jenkinsに対する拡張ポイントの情報でもある。
     */
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        /**
         * このビルド処理を適用できるジョブの確認。
         * 全ジョブに適用可能。
         */
        @SuppressWarnings("rawtypes")
        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        /**
         * 表示名。ビルド処理の追加時などに表示される。
         */
        @Override
        public String getDisplayName()
        {
            return Messages.SetenvBuilder_DisplayName();
        }
        
        /**
         * 利用可能なSetenvConfigの一覧を返す
         */
        public DescriptorExtensionList<SetenvConfig,Descriptor<SetenvConfig>> getSetenvConfigList()
        {
            return SetenvConfig.all();
        }
    }
}

