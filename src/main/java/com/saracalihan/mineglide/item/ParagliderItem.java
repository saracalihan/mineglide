package com.saracalihan.mineglide.item;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class ParagliderItem extends Item {
    public static final String ITEM_ID = "paraglider";

    private final Random random = new Random();
    private int tickCounter = 0;
    private static final int UPDATE_PER_SECOND = 10;
    private static final int UPDATE_INTERVAL = 20 / UPDATE_PER_SECOND; // Her saniye güncelle (20 tick = 1 saniye)
    private static final double VERTICAL_SPEED = 1;
    private static final double SINK_MIN = 0.2d; // 1.1m/s
    private static final double TERMIC_MIN = 0.7d; // 1.1m/s
    // private static final double DRAG_FORCE = 0.1;
    // private static Vec3d DRAG_FORCE_3D = new Vec3d(0, DRAG_FORCE, 0);
    private static volatile Vec3d valocity = new Vec3d(0, 0, 0);
    private static int valocityRemainingTick = 0;

    private static int delta = 1;

    public ParagliderItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, EquipmentSlot slot) {
        // Sadece item seçiliyse (elde tutuluyorsa) çalış
        if (world.isClient || !(entity instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity player = (PlayerEntity) entity;
        if (!(player.getMainHandStack().getItem() instanceof ParagliderItem)) {
            return;
        }
        player.setNoGravity(true);

        if (player.isOnGround()) {
            if (hasValocity()) {
                valocity = new Vec3d(0, 0, 0);
            }
            return;
        }

        // paraşüt giydiği için yerçekimi ivmesi değişir. Bunu biz hesaplarız
        // if (!player.hasNoGravity()) {
        // player.setNoGravity(true);
        // }

        // Her UPDATE_INTERVAL tick'te bir pozisyonu güncelle
        tickCounter++;
        if (tickCounter >= UPDATE_INTERVAL) {
            tickCounter = 0;
            Vec3d lift = calculateTermicLift(player);
            Vec3d grav = calculateSink(player);
            Vec3d verticalSpeed = calculateVerticalSpeed(player);

            Vec3d valo = grav.add(lift).add(verticalSpeed);

            Vec3d currentPos = player.getPos().add(valo);

            // System.out.println(String.format("val: %s, lift: %s, grv: %s", valo,
            // lift.toString(), grav.toString()));
            player.requestTeleport(currentPos.x, currentPos.y, currentPos.z);
        }
    }

    private Vec3d calculateSink(PlayerEntity player) {
        return new Vec3d(0, -1d * SINK_MIN / (double) UPDATE_INTERVAL, 0);
    }

    private Vec3d calculateVerticalSpeed(PlayerEntity player) {
        // Oyuncunun baktığı yönü al (yaw açısı)
        float yaw = player.getYaw();
        double yawRadians = Math.toRadians(yaw);

        // Yaw açısına göre x ve z bileşenlerini hesapla
        double x = -Math.sin(yawRadians) * VERTICAL_SPEED / (double) UPDATE_INTERVAL;
        double z = Math.cos(yawRadians) * VERTICAL_SPEED / (double) UPDATE_INTERVAL;

        return new Vec3d(x, 0, z);
    }

    private boolean playerOnTermic(Vec3d pos) {
        if (Math.floor(Math.abs(pos.x) / 10) % 2 == 0) {
            return true;
        }
        return false;
    }

    private Vec3d calculateTermicLift(PlayerEntity player) {
        // Termik alandaysam alanın ivmesine göre tick sayısı ve force hesaplanır.
        // termik ivme 1 saniye(20tick)sürecek şekilde hesaplanır ve 1 saniye bitene
        // kadar aynı ivme işletilir.

        valocityRemainingTick--;
        if (playerOnTermic(player.getPos())) {
            System.out.println("termikte");
            valocityRemainingTick = UPDATE_INTERVAL;

            // TODO: termik ivme getirme
            double yChange = TERMIC_MIN + random.nextDouble(); // ort termic 1.1ms
            return new Vec3d(0,
                    yChange /
                            (double) UPDATE_INTERVAL,
                    0);
        } else {
            return new Vec3d(0, 0, 0);
        }
    }

    private boolean hasValocity() {
        return new Vec3d(0, 0, 0).distanceTo(valocity) > 0;
    }
}
