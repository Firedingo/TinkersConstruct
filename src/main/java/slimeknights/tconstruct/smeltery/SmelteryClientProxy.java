package slimeknights.tconstruct.smeltery;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import slimeknights.tconstruct.common.ClientProxy;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.client.CustomTextureCreator;
import slimeknights.tconstruct.smeltery.client.CastingRenderer;
import slimeknights.tconstruct.smeltery.client.FaucetRenderer;
import slimeknights.tconstruct.smeltery.client.SmelteryRenderer;
import slimeknights.tconstruct.smeltery.client.TankRenderer;
import slimeknights.tconstruct.smeltery.tileentity.TileCastingBasin;
import slimeknights.tconstruct.smeltery.tileentity.TileCastingTable;
import slimeknights.tconstruct.smeltery.tileentity.TileFaucet;
import slimeknights.tconstruct.smeltery.tileentity.TileSmeltery;
import slimeknights.tconstruct.smeltery.tileentity.TileTank;

public class SmelteryClientProxy extends ClientProxy {

  @Override
  public void preInit() {
    super.preInit();

    MinecraftForge.EVENT_BUS.register(new SmelteryClientEvents());
  }

  @Override
  protected void registerModels() {
    // Blocks
    registerItemModel(Item.getItemFromBlock(TinkerSmeltery.smelteryController));
    registerItemModel(Item.getItemFromBlock(TinkerSmeltery.faucet));

    // TEs
    ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new TankRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileSmeltery.class, new SmelteryRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileFaucet.class, new FaucetRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileCastingTable.class, new CastingRenderer.Table());
    ClientRegistry.bindTileEntitySpecialRenderer(TileCastingBasin.class, new CastingRenderer.Basin());


    // Items
    final ResourceLocation castLoc = SmelteryClientEvents.locBlankCast;
    CustomTextureCreator.castModelLocation = new ResourceLocation(castLoc.getResourceDomain(), "item/" + castLoc.getResourcePath());
    ModelLoader.setCustomMeshDefinition(TinkerSmeltery.cast, new PatternMeshDefinition(castLoc));

    if(Config.claycasts) {
      final ResourceLocation clayCastLoc = SmelteryClientEvents.locClayCast;
      CustomTextureCreator.castModelLocation = new ResourceLocation(clayCastLoc.getResourceDomain(),
                                                                    "item/" + clayCastLoc.getResourcePath());
      ModelLoader.setCustomMeshDefinition(TinkerSmeltery.clayCast, new PatternMeshDefinition(clayCastLoc));
    }

    TinkerSmeltery.castCustom.registerItemModels("cast_");
  }
}
